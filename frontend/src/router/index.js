import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AddCategoryForm from '../components/AddCategoryForm';
import EditCategoryForm from '../components/EditCategoryForm';
import EditVestForm from '../components/EditVestForm.vue';
import EditKorisnikForm from '../components/EditKorisnikaForm.vue';
import AddKorisnikForm from '../components/AddKorisnikForm.vue';




Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/kategorije',
    name: 'Kategorije',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Kategorije.vue')
  },
  {
    path: '/kategorije/:id',
    name: 'single-kategorija',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/SingleKategorija.vue')
  },
  {
    path: '/filter-category/:categoryId',
    name: 'filter-category',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/FiltriraneVestiPoKategoriji.vue')
  },
  {
    path: '/add-category',
    name: 'AddCategory',
    component: AddCategoryForm
  },
  {
    path: '/edit-category/:categoryId',
    name: 'EditCategory',
    component: EditCategoryForm
  },
  {
    path: '/vesti',
    name: 'Vesti',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Vesti.vue')
  },
  {
    path: '/vesti/:id',
    name: 'single-vest',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/SingleVest.vue')
  },
  {
    path: '/edit-vest/:vestId',
    name: 'EditVest',
    component: EditVestForm
  },
  {
    path: '/korisnici',
    name: 'Korisnici',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Korisnici.vue')
  },
  {
    path: '/korisnici/:id',
    name: 'single-korisnik',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/SingleKorisnik.vue')
  },
  {
    path: '/edit-korisnika/:korisnikId',
    name: 'EditKorisnik',
    component: EditKorisnikForm
  },
  {
    path: '/add-korisnika',
    name: 'AddKorisnik',
    component: AddKorisnikForm
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  {
    path: '/homePage',
    name: 'HomePage',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/HomePage.vue')
  },
  {
    path: '/najcitanijePage',
    name: 'NajcitanijePage',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Najcitanije.vue')
  },
  {
    path: '/homePagePoKategoriji',
    name: 'HomePagePoKategoriji',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/HomePagePoKategoriji.vue')
  },

]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.authRequired) {
    const jwt = localStorage.getItem('jwt');

    if (!jwt) {
      next({name: 'Login'});
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    const expDate = new Date(payload.exp * 1000);
    if (expDate < new Date()) {
      next({name: 'Login'});
      return;
    }
  }

  next();
});

export default router
