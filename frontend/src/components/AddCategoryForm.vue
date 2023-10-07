<template>
  <div class="add-category-form">
    <h2>Add Category</h2>

    <form @submit.prevent="createCategory">
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="category.name" required>
      </div>

      <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" v-model="category.description" required></textarea>
      </div>

      <button type="submit" class="btn btn-primary">Add</button>
    </form>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      category: {
        name: '',
        description: ''
      },
      errorMessage: ''
    };
  },
  methods: {
    createCategory() {
      // Send an API request to create the category (assuming you have an API endpoint)
      this.$axios.post('/api/kategorije', this.category)
          // eslint-disable-next-line no-unused-vars
          .then(response => {
            // Category created successfully, navigate back to the category list
            this.$router.push('/kategorije'); // Adjust the route as per your actual category list route
          })
          .catch(error => {
            console.error('Failed to create category:', error);
            this.errorMessage = 'Ne mozete da ostavite prazno polje ili da napravite kategoriju koja vec postoji';
          });
    }
  }
}
</script>