<template>
  <div class="najcitanije-page">
    <h1>Najcitanije vesti</h1>
    <ul>
      <li v-for="vest in sortedVesti" :key="vest.id">
        <h2>{{ vest.title }}</h2>
        <p>{{ truncateText(vest.text) }}</p>
        <p>Kategorija: {{ vest.kategorija }}</p>
        <p>Datum izbacivanja: {{ formatDate(vest.date_of_creation) }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Najcitanije",
  data() {
    return {
      vesti: [],
    };
  },
  created() {
    this.fetchNajcitanije();
  },
  computed: {
    sortedVesti() {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      return this.vesti
          .sort((a, b) => b.number_of_visits - a.number_of_visits)
          .slice(0, 10);
    },
  },
  methods: {
    fetchNajcitanije() {
      // Adjust the API call to fetch the 10 most read news articles in the last 30 days
      const thirtyDaysAgo = new Date();
      thirtyDaysAgo.setDate(thirtyDaysAgo.getDate() - 30);

      this.$axios
          .get("/api/vesti", {
            params: {
              startDate: thirtyDaysAgo.toISOString(),
              endDate: new Date().toISOString(),
            },
          })
          .then((response) => {
            this.vesti = response.data;
          })
          .catch((error) => {
            console.error("Failed to fetch najcitanije vesti:", error);
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
  },
};
</script>