<template>
  <div class="edit-category-form">
    <h2>Edit Category</h2>

    <form @submit.prevent="updateCategory">
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="category.name" required>
      </div>

      <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" v-model="category.description" required></textarea>
      </div>

      <button type="submit" class="btn btn-primary">Update</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      category: {
        name: '',
        description: ''
      }
    };
  },
  created() {
    const categoryId = this.$route.params.categoryId;
    this.fetchCategory(categoryId);
  },
  methods: {
    fetchCategory(categoryId) {
      // Send an API request to fetch the category details (assuming you have an API endpoint)
      this.$axios.get(`/api/kategorije/${categoryId}`)
          .then(response => {
            this.category = response.data;
          })
          .catch(error => {
            console.error('Failed to fetch category:', error);
          });
    },
    updateCategory() {
      // Send an API request to update the category (assuming you have an API endpoint)
      const categoryId = this.$route.params.categoryId;
      this.$axios.put(`/api/kategorije/${categoryId}`, this.category)
          .then(() => {
            // Category updated successfully, navigate back to the category list
            this.$router.push('/kategorije'); // Adjust the route as per your actual category list route
          })
          .catch(error => {
            console.error('Failed to update category:', error);
          });
    }
  }
}
</script>