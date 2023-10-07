<template>
  <div>
    <kategorija :kategorija="kategorija"></kategorija>
  </div>
</template>

<script>
import Kategorija from "../components/Kategorija.vue";
export default {
  name: "SingleKategorija",
  components: {Kategorija},
  data() {
    return {
      kategorija: {},
    }
  },
  created() {
    this.$axios.get(`/api/kategorije/${this.$route.params.id}`).then((response) => {
      this.kategorija = response.data;
    });
  }
}
</script>

<style scoped>

</style>