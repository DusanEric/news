<template>
  <div class="home-page">
    <h1>Filtriraj po kategoriji</h1>

    <select v-model="selectedCategory" @change="filterByCategory">
      <option value="">Sve kategorije</option>
      <option v-for="kategorija in kategorije" :key="kategorija.id" :value="kategorija.id">{{ kategorija.name }}</option>
    </select>

    <ul>
      <li v-for="vest in filteredVesti" :key="vest.id" @click="povecajPosete(vest)">
        <h2>
          <router-link :to="`/vesti/${vest.id}`" tag="a" class="nav-link" :class="{ active: $route.path === `/vesti/${vest.id}` }" target="_blank">
            {{ vest.title }}
          </router-link>
        </h2>
        <p>{{ truncateText(vest.text) }}</p>
        <p>Datum objave: {{ formatDate(vest.date_of_creation) }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HomePagePoKategoriji",
  data() {
    return {
      vesti: [],
      kategorije: [],
      selectedCategory: "",
    };
  },
  created() {
    this.fetchVesti();
    this.fetchKategorije();
  },
  computed: {
    filteredVesti() {
      if (this.selectedCategory) {
        return this.vesti.filter((vest) => vest.kategorija === this.selectedCategory);
      }
      return this.vesti;
    },
  },
  methods: {
    fetchVesti() {
      this.$axios
          .get("/api/vesti")
          .then((response) => {
            this.vesti = response.data;
          })
          .catch((error) => {
            console.error("Failed to fetch vesti:", error);
          });
    },
    fetchKategorije() {
      this.$axios
          .get("/api/kategorije")
          .then((response) => {
            this.kategorije = response.data;
          })
          .catch((error) => {
            console.error("Failed to fetch kategorije:", error);
          });
    },
    truncateText(text) {
      if (text.length > 5) {
        return text.slice(0, 5) + "...";
      }
      return text;
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const options = {
        year: "numeric",
        month: "long",
        day: "numeric",
      };
      return date.toLocaleDateString(undefined, options);
    },
    povecajPosete(vest) {
      const br_poseta = vest.number_of_visits + 1;
      vest.number_of_visits = br_poseta;
      this.$axios
          .put(`/api/vesti/${vest.id}`, vest)
          .catch((error) => {
            console.error("Failed to increment vest:", error);
          });
    },
    filterByCategory() {
      // Fetch vesti again based on the selected category
      this.fetchVesti();
    },
  },
};
</script>