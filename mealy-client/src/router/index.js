import Vue from 'vue'
import VueRouter from 'vue-router'
import IngredientsList from '@/components/ingredient/IngredientsList'
import Ingredient from '@/components/ingredient/Ingredient'

Vue.use(VueRouter)
export default new VueRouter({
  routes: [
    {
        path: '/',
        name: 'IngredientsList',
        component: IngredientsList
    },
    {
        path: '/ingredient/:ingredientId',
        name: 'Ingredient',
        props: true,
        component: Ingredient
    }
  ],
  mode: 'history'
})