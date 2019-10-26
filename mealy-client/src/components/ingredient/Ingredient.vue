<template>
  <v-container v-if="loading">
    <div class="text-xs-center">
      <v-progress-circular
        indeterminate
        :size="150"
        :width="8"
        color="green">
      </v-progress-circular>
    </div>
  </v-container>

  <v-container v-else>
    <v-layout row wrap>
      <v-flex xs12>
        <div>{{singleIngredient}}</div>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import IngredientApi from '@/services/IngredientApi'
  export default {
    props: ['ingredientId'],
    data () {
      return {
        singleIngredient: {},
        loading: true
      }
    },
    mounted () {
      IngredientApi.fetchSingleIngredient(this.ingredientId)
      .then(response => {
        this.singleIngredient = response
        this.loading = false
      })
      .catch(error => {
        console.log(error)
      })
    },
    methods: {
      back () {
        this.$router.push('/')
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .v-progress-circular
    margin: 1rem
</style>