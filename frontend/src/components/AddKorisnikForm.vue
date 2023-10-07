<template>
  <div class="add-korisnik-form">
    <h2>Add Korisnik</h2>

    <form @submit.prevent="createKorisnik">
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="korisnik.name" required>
      </div>

      <div class="form-group">
        <label for="lastname">Lastname:</label>
        <textarea id="lastname" v-model="korisnik.lastname" required></textarea>
      </div>

      <div class="form-group">
        <label for="email">Email:</label>
        <input type="text" id="email" v-model="korisnik.email" required>
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <textarea id="password" v-model="korisnik.password" required></textarea>
      </div>

      <div class="form-group">
        <label for="type">Type:</label>
        <input type="text" id="type" v-model="korisnik.type" required>
      </div>

      <div class="form-group">
        <label for="status">Status:</label>
        <textarea id="status" v-model="korisnik.status" required></textarea>
      </div>

      <button type="submit" class="btn btn-primary">Add</button>

      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
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
        password: '',
        type: '',
        status: ''
      },
      errorMessage: ''
    };
  },
  methods: {
    createKorisnik() {
      // Send an API request to create the category (assuming you have an API endpoint)
      this.$axios.post('/api/korisnici', this.korisnik)
          // eslint-disable-next-line no-unused-vars
          .then(response => {
            // Category created successfully, navigate back to the category list
            this.$router.push('/korisnici'); // Adjust the route as per your actual category list route
          })
          .catch(error => {
            console.error('Failed to create korisnik:', error);
            this.errorMessage = 'Ne mozete da ostavite prazno polje ili da koristite email koji vec postoji';
          });
    }
  }
}
</script>