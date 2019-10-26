import axios from 'axios'

export default {
  fetchIngredientsList () {
    return axios.get('ingredient/getAllIngredients/')
      .then(response => {
        return response.data
      })
  },

  fetchSingleIngredient (ingredientId) {
    return axios.get('ingredient/getIngredient/' + ingredientId)
      .then(response => {
        return response.data
      })
  }
}