<template>
  <div class="kategorije">
    <h1 class="mt-4">Kategorije</h1>

    <div class="row">
      <div class="col-12">
        <table class="table">
          <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="kategorija in kategorije" :key="kategorija.id" @click="filtriraj(kategorija)">
            <th scope="row">{{ kategorija.id }}</th>
            <td>{{ kategorija.name | capitalize }}</td>
            <td>{{ kategorija.description | shortText }}</td>
            <td>
              <button class="btn btn-primary" @click="editKategorija(kategorija)">Edit</button>
            </td>
            <td>
              <button class="btn btn-danger" @click="deleteKategorija(kategorija)">Delete</button>
            </td>
          </tr>
          </tbody>
        </table>
        <button class="btn btn-primary mt-4" @click="addKategorija">Add Kategorija</button>
      </div>
<!--      <div class="col-6">-->
<!--        <kategorija v-if="selectedKategorija" :kategorija="selectedKategorija"></kategorija>-->
<!--      </div>-->
    </div>
  </div>
</template>

<script>
// import Kategorija from "../components/Kategorija";

export default {
  // components: {Kategorija},
  filters: {
    shortText(value) {
      if (value.length < 30) {
        return value;
      }
      return value.slice(0, 30) + '...'
    }
  },
  data() {
    return {
      selectedKategorija: null,
      kategorije: []
    }
  },
  created() {
    this.$axios.get('/api/kategorije').then((response) => {
      this.kategorije = response.data;
    });
  },
  methods: {
    addKategorija() {
      this.selectedKategorija = null; // Clear the selected category
      // Navigate to the form for adding a new category (assuming you have a route defined)
      this.$router.push('/add-category');
    },
    editKategorija(kategorija) {
      this.selectedKategorija = null; // Clear the selected category
      // Navigate to the form for editing a category (assuming you have a route defined)
      this.$router.push(`/edit-category/${kategorija.id}`);
    },
    deleteKategorija(kategorija) {
        // Make an API request to delete the category (assuming you have an API endpoint)
        this.$axios.delete(`/api/kategorije/${kategorija.id}`)
            .then(() => {
              // Remove the deleted category from the list
              this.kategorije = this.kategorije.filter(cat => cat.id !== kategorija.id);

            })
            .catch(error => {
              console.error('Failed to delete category:', error);
            });

    },
    filtriraj(kategorija){
      this.$router.push(`/filter-category/${kategorija.id}`);
    }
  }
}
</script>
