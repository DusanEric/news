<template>
  <div class="edit-vest-form">
    <h2>Edit Vest</h2>

    <form @submit.prevent="updateVest">

      <div class="form-group">
        <label for="title">Title:</label>
        <textarea id="title" v-model="vest.title" required></textarea>
      </div>

      <div class="form-group">
        <label for="text">Text:</label>
        <textarea id="text" v-model="vest.text" required></textarea>
      </div>

      <div class="form-group">
        <label for="number_of_visits">Number of visits:</label>
        <textarea id="number_of_visits" v-model="vest.number_of_visits" required></textarea>
      </div>

      <div class="form-group">
        <label for="date_of_creation">Date:</label>
        <textarea id="date_of_creation" v-model="vest.date_of_creation" required></textarea>
      </div>

      <div class="form-group">
        <label for="description">Autor:</label>
        <textarea id="description" v-model="vest.autor" required></textarea>
      </div>

      <div class="form-group">
        <label for="kategorija">Kategorija:</label>
        <textarea id="kategorija" v-model="vest.kategorija" required></textarea>
      </div>

      <div class="form-group">
        <label for="tagovi">Tagovi:</label>
        <textarea id="tagovi" v-model="vest.tagovi" required></textarea>
      </div>

      <button type="submit" class="btn btn-primary">Update</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      vest: {
        title: '',
        text: '',
        number_of_visits: '',
        date_of_creation: '',
        autor: '',
        kategorija: '',
        tagovi: ''
      }
    };
  },
  created() {
    const vestId = this.$route.params.vestId;
    this.fetchVest(vestId);
  },
  methods: {
    fetchVest(vestId) {
      this.$axios.get(`/api/vesti/${vestId}`)
          .then(response => {
            this.vest = response.data;
          })
          .catch(error => {
            console.error('Failed to fetch vest:', error);
          });
    },
    updateVest() {
      const vestId = this.$route.params.vestId;
      this.$axios.put(`/api/vesti/${vestId}`, this.vest)
          .then(() => {
            this.$router.push('/vesti'); // Adjust the route as per your actual category list route
          })
          .catch(error => {
            console.error('Failed to update vest:', error);
          });
    }
  }
}
</script>