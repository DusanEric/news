<template>
  <div>
    <korisnik :korisnik="korisnik"></korisnik>
  </div>
</template>

<script>
import Korisnik from "../components/Korisnik.vue";
export default {
  name: "SingleKorisnik",
  components: {Korisnik},
  data() {
    return {
      korisnik: {},
    }
  },
  created() {
    this.$axios.get(`/api/korisnici/${this.$route.params.id}`).then((response) => {
      this.korisnik = response.data;
    });
  }
}
</script>

<style scoped>

</style>