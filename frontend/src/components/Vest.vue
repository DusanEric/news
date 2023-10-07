<template>
  <div>
    <h2>
      Vest: {{ vest.title }}
    </h2>

    <h5>Opis:</h5>
    <p>
      {{ vest.text }} <br><br>
      {{ vest.autor }} <br><br>
      {{ vest.date_of_creation }} <br><br>
      {{ vest.tagovi }} <br><br>
    </p>
    <br><br>
    <form @submit="dodajKomentar">
      <div>
        <label for="ime">Ime čitaoca:</label>
        <input type="text" id="ime" v-model="imeCitaoca" required>
      </div>
      <div>
        <label for="tekst">Tekst komentara:</label>
        <textarea id="tekst" v-model="tekstKomentara" required></textarea>
      </div>
      <div>
        <button type="submit">Dodaj komentar</button>
      </div>
    </form>
    <br><br>
    <div v-for="komentar in filteredKomentari" :key="komentar.id">
      <p>{{ komentar.text }}</p>
      <p>{{ komentar.autor }}</p>
    </div>
  </div>
</template>

<script>

export default {
  name: "Vest",
  props: {
    vest: {
      type: Object,
      required: true,
    }
  },

  data() {
    return {
      filteredKomentari: [],
      komentari: [],
      imeCitaoca: '',
      tekstKomentara: ''
    }
  },

  created() {
    this.fetchKomentare();
  },
  methods: {
    fetchKomentare() {
      this.$axios.get("/api/komentari")
          .then((response) => {
            this.komentari = response.data;
            // Filter the comments based on vest.id
            const filteredKomentari = this.komentari.filter((komentar) => komentar.vest_id === this.vest.id);
            // Assign the filtered comments to a component property or display them as needed
            this.filteredKomentari = filteredKomentari;          })
          .catch((error) => {
            console.error("Failed to fetch komentari:", error);
          });
    },
    dodajKomentar() {
      if (this.imeCitaoca && this.tekstKomentara) {
        const noviKomentar = {
          autor: this.imeCitaoca,
          text: this.tekstKomentara,
          date: "2023-04-07",
          vest_id: this.vest.id
        };

        this.$axios.post('/api/komentari', noviKomentar)
            .then(() => {
              // Clear the form fields
              this.imeCitaoca = '';
              this.tekstKomentara = '';

              // Fetch the updated list of comments
              this.fetchKomentare();
            })
            .catch((error) => {
              console.error('Failed to add comment:', error);
            });
      }
    }
  }
}
</script>

<style scoped>

</style>