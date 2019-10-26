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

  <v-container v-else grid-list-xl>
    <v-layout wrap>
      <v-flex xs4 v-for="(ingredient, index) in ingredientsList" :key="index" mb-2>
        <v-card>
          <v-card-title primary-title>
            <div>
              <h2>{{ingredient.name}}</h2>
              <div>{{ingredient.description}}</div>
            </div>
          </v-card-title>
          <v-card-actions class="justify-center">
            <v-btn text
              color="green"
              @click="singleIngredient(ingredient.id)"
              >View</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import IngredientApi from '@/services/IngredientApi'
  export default {
    data () {
      return {
        ingredientsList: [],
        loading: true
      }
    },
    mounted () {
      IngredientApi.fetchIngredientsList()
      .then(response => {
        this.ingredientsList = response
        this.loading = false
      })
      .catch(error => {
        console.log(error)
      })
    },
    methods: {
      singleIngredient (ingredientId) {
        this.$router.push('/ingredient/' + ingredientId)
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .v-progress-circular
    margin: 1rem
</style>