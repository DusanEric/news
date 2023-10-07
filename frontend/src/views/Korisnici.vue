<template>
  <div>
    <h2>Korisnici</h2>
    <table>
      <thead>
      <tr>
        <th>Ime</th>
        <th>Prezime</th>
        <th>Email</th>
        <th>Tip korisnika</th>
        <th>Akcije</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="korisnik in korisnici" :key="korisnik.id">
        <td>{{ korisnik.name }}</td>
        <td>{{ korisnik.lastname }}</td>
        <td>{{ korisnik.email }}</td>
        <td>{{ korisnik.type }}</td>
        <td>
          <button @click="editKorisnika(korisnik)">Edit</button>
          <button v-if="korisnik.type === 'creator'" @click="promeniStatus(korisnik)">
            {{ korisnik.status == 1 ? 'Deaktiviraj' : 'Aktiviraj' }}
          </button>
        </td>
      </tr>
      </tbody>
    </table>

    <button @click="dodajKorisnika">Dodaj korisnika</button>
  </div>
</template>

<script>
export default {
  name: "Korisnici",
  data() {
    return {
      korisnici: [],
    };
  },
  created() {
    this.ucitajKorisnike();
  },
  methods: {
    ucitajKorisnike() {
      // Logic to fetch the list of korisnici from the server
      // Replace this with your actual API call using axios or other HTTP client
      this.$axios.get("/api/korisnici").then((response) => {
        this.korisnici = response.data;
      });
    },
    dodajKorisnika() {
      // Logic to navigate to the form for adding a new korisnik
      // Replace this with your actual navigation code
      this.$router.push("/add-korisnika");
    },
    editKorisnika(korisnik) {
      this.$router.push(`/edit-korisnika/${korisnik.id}`);
    },
    promeniStatus(korisnik) {
      // Logic to change the status of the selected korisnik (aktiviraj/deaktiviraj)
      // Replace this with your actual API call to update the korisnik's status
      let newStatus = korisnik.status ;
      if (newStatus == true){
        newStatus = false;
      }else {
        newStatus = true;
      }
      // let newStatus = true;
      // if(status == 1){
      //   newStatus = false;
      // }else{
      //   newStatus =true;
      // }
      korisnik.status = newStatus
      this.$axios
          .put(`/api/korisnici/${korisnik.id}`, korisnik)
          ;
    },
  },
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid black;
  padding: 8px;
}
</style>