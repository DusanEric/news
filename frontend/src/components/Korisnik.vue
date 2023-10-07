<template>
  <div>
    <h2>
      Vest: {{ korisnik.email }}
    </h2>

    <h5>Opis:</h5>
    <p>
      {{ korisnik.name }} <br><br>
      {{ korisnik.lastname }} <br><br>
      {{ korisnik.password }} <br><br>
      {{ korisnik.type }} <br><br>
      {{ korisnik.status }} <br><br>
    </p>
  </div>
</template>

<script>

export default {
  name: "Korisnik",
  props: {
    korisnik: {
      type: Object,
      required: true,
    }
  },
}
</script>

<style scoped>

</style>