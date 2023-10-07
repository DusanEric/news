<template>
  <div>
    <h2>
      Kategorija: {{ kategorija.name }}
    </h2>

    <h5>Opis:</h5>
    <p>
      {{ kategorija.description }} <br><br>
    </p>
  </div>
</template>

<script>

export default {
  name: "Kategorija",
  props: {
    kategorija: {
      type: Object,
      required: true,
    }
  },
}
</script>

<style scoped>

</style>