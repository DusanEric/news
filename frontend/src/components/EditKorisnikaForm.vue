<template>
  <div class="edit-korisnik-form">
    <h2>Edit Korisnika</h2>

    <form @submit.prevent="updateKorisnika">

      <div class="form-group">
        <label for="name">Name:</label>
        <textarea id="name" v-model="korisnik.name" required></textarea>
      </div>

      <div class="form-group">
        <label for="lastname">Lastname:</label>
        <textarea id="lastname" v-model="korisnik.lastname" required></textarea>
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <textarea id="email" v-model="korisnik.email" required></textarea>
      </div>

      <div class="form-group">
        <label for="type">Type:</label>
        <textarea id="type" v-model="korisnik.type" required></textarea>
      </div>

      <div class="form-group">
        <label for="status">Status:</label>
        <textarea id="status" v-model="korisnik.status" required></textarea>
      </div>

      <button type="submit" class="btn btn-primary">Update</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      korisnik: {
        name: '',
        lastname: '',
        email: '',
        password:'',
        type: '',
        status: ''
      }
    };
  },
  created() {
    const korisnikId = this.$route.params.korisnikId;
    this.fetchVest(korisnikId);
  },
  methods: {
    fetchVest(korisnikId) {
      this.$axios.get(`/api/korisnici/${korisnikId}`)
          .then(response => {
            this.korisnik = response.data;
          })
          .catch(error => {
            console.error('Failed to fetch korisnika:', error);
          });
    },
    updateKorisnika() {
      const korisnikId = this.$route.params.korisnikId;
      this.$axios.put(`/api/korisnici/${korisnikId}`, this.korisnik)
          .then(() => {
            this.$router.push('/korisnici'); // Adjust the route as per your actual category list route
          })
          .catch(error => {
            console.error('Failed to update korisnika:', error);
          });
    }
  }
}
</script>