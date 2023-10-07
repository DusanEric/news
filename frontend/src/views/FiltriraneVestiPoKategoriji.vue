<template>
  <div class="vesti">
    <h1 class="mt-4">Vesti</h1>

    <div class="row">
      <div class="col-4">
        <table class="table">
          <thead>
          <tr>
            <th scope="col">Naslov</th>
            <th scope="col">Autor</th>
            <th scope="col">Datum kreiranja</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="vest in vesti" :key="vest.id">
            <td>
              <!--              <a :href="`/vesti/${vest.id}`" >{{ vest.title }}</a>-->
              <!--              <router-link :to="{path: '/vesti/${vest.id}'}" tag="a" class="nav-link" :class="{active: $route.path === '/vesti/${vest.id}'}" >{{ vest.title }}</router-link>-->
              <!--              <router-link :to="{name: 'single-vest'}" tag="a" class="nav-link" :class="{active: $route.name === 'single-vest'}" >{{ vest.title }}</router-link>-->
              <router-link :to="`/vesti/${vest.id}`" tag="a" class="nav-link" :class="{ active: $route.path === `/vesti/${vest.id}` }" target="_blank">
                {{ vest.title }}
              </router-link>
            </td>
            <td>{{ vest.autor }}</td>
            <td>{{ vest.date_of_creation }}</td>
            <td>
              <button class="btn btn-primary" @click="editVest(vest)">Edit</button>
            </td>
            <td>
              <button class="btn btn-danger" @click="deleteVest(vest.id)">Delete</button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="col-6">
        <form>
          <h3>Dodaj novu vest</h3>
          <div class="form-group">
            <label for="naslov">Naslov:</label>
            <input type="text" class="form-control" id="naslov" v-model="novaNaslov">
          </div>
          <div class="form-group">
            <label for="kategorija">Kategorija:</label>
            <select class="form-control" id="kategorija" v-model="novaKategorija">
              <option v-for="kategorija in kategorije" :key="kategorija.id" :value="kategorija.id">{{ kategorija.name }}</option>
            </select>
          </div>
          <div class="form-group">
            <label for="tekst">Tekst:</label>
            <textarea class="form-control" id="tekst" rows="3" v-model="noviTekst"></textarea>
          </div>
          <div class="form-group">
            <label for="tagovi">Tagovi:</label>
            <input type="text" class="form-control" id="tagovi" v-model="noviTagovi">
          </div>
          <button class="btn btn-primary" @click="addVest">Dodaj</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Vesti",
  data() {
    return {
      selectedVest: null,
      kategorijaName: '',
      vesti: [],
      kategorije: [],
      novaNaslov: "",
      novaKategorija: null,
      noviTekst: "",
      noviTagovi: "",
    }
  },
  created() {
    const kategorijaId = this.$route.params.categoryId;
    this.fetchKategorije(kategorijaId);
    this.fetchVesti();
  },
  methods: {
    fetchVesti() {
      this.$axios.get("/api/vesti")
          .then((response) => {
            this.vesti = response.data.filter((vest) => vest.kategorija === this.kategorijaName);
          })
          .catch((error) => {
            console.error("Failed to fetch vesti:", error);
          });
    },
    fetchKategorije(kategorijaId) {
      this.$axios.get(`/api/kategorije/${kategorijaId}`)
          .then((response) => {
            this.kategorije = response.data;
            this.kategorijaName = response.data.name;
          })
          .catch((error) => {
            console.error("Failed to fetch kategorije:", error);
          });
    },
    addVest() {

      const userToken = localStorage.getItem("token");
      // // Decode the token to extract the user's name
      // // eslint-disable-next-line no-undef
      // const decodedToken = decodeToken(userToken);

      const jwt = require("jsonwebtoken");

      const decodedToken = jwt.verify(userToken, "secret");

      const novaVest = {
        title: this.novaNaslov,
        text: this.noviTekst,
        number_of_visits: 1,
        date_of_creation: "2023-06-26",
        autor: decodedToken.name,
        kategorija: this.novaKategorija,
        tagovi: this.noviTagovi,
      };

      this.$axios.post("/api/vesti", novaVest)
          // eslint-disable-next-line no-unused-vars
          .then((response) => {
            this.novaNaslov = "";
            this.novaKategorija = null;
            this.noviTekst = "";
            this.noviTagovi = "";
            this.fetchVesti();
          })
          .catch((error) => {
            console.error("Failed to add vest:", error);
          });
    },
    // eslint-disable-next-line no-unused-vars
    editVest(vest) {
      this.$router.push(`/edit-vest/${vest.id}`);
    },
    deleteVest(vestId) {
      this.$axios.delete(`/api/vesti/${vestId}`)
          .then(() => {
            this.fetchVesti();
          })
          .catch((error) => {
            console.error("Failed to delete vest:", error);
          });
    },
  },
}
</script>

<style scoped>

</style>