<template>
  <div class="home-page">
    <h1>Najnovije vesti</h1>
    <ul>
      <li v-for="vest in sortedVesti" :key="vest.id" @click="povecajPosete(vest)">
        <h2>
          <router-link :to="`/vesti/${vest.id}`" tag="a" class="nav-link" :class="{ active: $route.path === `/vesti/${vest.id}` }" target="_blank">
            {{ vest.title }}
          </router-link>
        </h2>
        <p>{{ truncateText(vest.text) }}</p>
        <p>Kategorija: {{ vest.kategorija }}</p>
        <p>Datum izbacivanja: {{ formatDate(vest.date_of_creation) }}</p>
      </li>
    </ul>

    <div class="pagination">
      <button @click="goToPreviousPage" :disabled="currentPage === 1">Previous</button>
      <span>Page {{ currentPage }}</span>
      <button @click="goToNextPage" :disabled="currentPage === Math.ceil(vesti.length / pageSize)">Next</button>
    </div>
  </div>
</template>
<script>
export default {
  name: "HomePage",
  data() {
    return {
      vesti: [],
      currentPage: 1,
      pageSize: 10,
    };
  },
  created() {
    this.fetchVesti();
  },
  computed: {
    sortedVesti() {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      return this.vesti
          .sort((a, b) => new Date(b.date_of_creation) - new Date(a.date_of_creation))
          .slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
    },
  },
  methods: {
    fetchVesti() {

      const offset = (this.currentPage - 1) * this.pageSize;
      const limit = this.pageSize;

      this.$axios.get("/api/vesti", {
        params: {
          offset,
          limit,
        },
      })
          .then((response) => {
            this.vesti = response.data;
          })
          .catch((error) => {
            console.error("Failed to fetch vesti:", error);
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
    povecajPosete(vest){
      const br_poseta = vest.number_of_visits + 1;

      vest.number_of_visits = br_poseta;
      this.$axios
          .put(`/api/vesti/${vest.id}`, vest)
          .catch((error) => {
            console.error("Failed to increment vest:", error);
          });
    },
    goToPreviousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
        this.fetchVesti();
      }
    },
    goToNextPage() {
      if (this.currentPage < Math.ceil(this.vesti.length / this.pageSize)) {
        this.currentPage++;
        this.fetchVesti();
      }
    },
  },
};
</script>
