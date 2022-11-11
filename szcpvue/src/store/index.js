import Vue from 'vue'
import Vuex from 'vuex'

import user from './module/user'
import app from './module/app'
import sysicon from './module/sysicon'
import sysmenu from './module/sysmenu'
import sysorgan from './module/sysorgan'
import sysrole from './module/sysrole'
// import sysmarkdown from './module/sysmarkdown'
import sysarticlemenu  from './module/sysarticlemenu'
import sysarticle  from './module/sysarticle'

Vue.use(Vuex)

export default new Vuex.Store({

  state: {
  
  },
  mutations: {
    
  },
  actions: {
    //
  },
  modules: {
    user,
    app,
    sysicon,
    sysmenu,
    sysorgan,
    sysrole,
    sysarticlemenu,
    sysarticle
  }
})
