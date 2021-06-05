import Main from '@/components/main'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  title: { String|Number|Function }
 *         显示在侧边栏、面包屑和标签栏的文字
 *         使用'{{ 多语言字段 }}'形式结合多语言使用，例子看多语言的路由配置;
 *         可以传入一个回调函数，参数是当前路由对象，例子看动态路由和带参路由
 *  hideInBread: (false) 设为true后此级路由将不会出现在面包屑中，示例看QQ群路由配置
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面在切换标签后不会缓存，如果需要缓存，无需设置这个字段，而且需要设置页面组件name属性和路由配置的name一致
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 *  beforeCloseName: (-) 设置该字段，则在关闭当前tab页时会去'@/router/before-close.js'里寻找该字段名对应的方法，作为关闭前的钩子函数
 * }
 */

export default [
  {
    path: '/',
    name: '首页',
    redirect: '/md'
  },

  {
    path: '/header',
    name: '/header',
    meta: {
      title: '顶部导航栏',
      hideInMenu: true
    },
    component: () => import('@/webview/index/header.vue'),
    children: [
      {
        path: '/index',
        name: 'index',
        meta: {
          title: '左侧菜单',
          hideInMenu: true
        },
        component: () => import('@/webview/index/leftMenu.vue'),
        children: [
          {
            path: '/md',
            name: 'md',
            meta: {
              title: 'java全栈知识点',
              hideInMenu: true
            },
            component: () => import('@/webview/index/index.vue'),

          },

          {
            path: '/about',
            name: 'about',
            meta: {
              title: '关于',
              hideInMenu: true
            },
            component: () => import('@/webview/about/about.vue'),
          },


        ]
      },

      

    ]
  },


  {
    path: '/sys/login',
    name: 'sys/login',
    meta: {
      title: 'Login - 登录',
      hideInMenu: true
    },
    component: () => import('@/view/login/login.vue')
  },
  {
    path: '/sys',
    name: '_home',
    redirect: '/sys/home',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: '/sys/home',
        name: 'sys/home',
        meta: {
          hideInMenu: true,
          title: '首页',
          notCache: true,
          icon: 'md-home'
        },
        component: () => import('@/view/home')
      }
    ]
  },

  {
    path: '',
    name: '文章管理',
    meta: {
      hideInMenu: true,
      icon: 'logo-buffer',
      title: '文章管理'
    },
    component: Main,
    children: [
      {
        path: 'sys/article',
        name: 'sys/article',
        meta: {
          icon: 'md-arrow-dropdown-circle',
          title: '文章管理'
        },
        component: () => import('@/view/sys/articlesManagerment/article.vue')
      },
      {
        path: 'sys/articleMenu',
        name: 'sys/articleMenu',
        meta: {
          icon: 'md-trending-up',
          title: '文章树管理'
        },
        component: () => import('@/view/sys/articlesManagerment/articleMenu.vue')
      },

    ]
  },
  {
    path: '',
    name: '基金管理',
    meta: {
      hideInMenu: true,
      icon: 'logo-buffer',
      title: '基金管理'
    },
    component: Main,
    children: [
      {
        path: 'sys/fundInfo',
        name: 'sys/fundInfo',
        meta: {
          icon: 'md-arrow-dropdown-circle',
          title: '我的基金'
        },
        component: () => import('@/view/sys/fundManagerment/fundInfo.vue')
      },
      {
        path: 'sys/fundType',
        name: 'sys/fundType',
        meta: {
          icon: 'md-trending-up',
          title: '基金类型'
        },
        component: () => import('@/view/sys/fundManagerment/fundType.vue')
      },

    ]
  },


  {
    path: '',
    name: '系统管理',
    meta: {
      hideInMenu: true,
      icon: 'logo-buffer',
      title: '系统管理'
    },
    component: Main,
    children: [
      {
        path: 'sys/adminUser',
        name: 'sys/adminUser',
        meta: {
          icon: 'md-arrow-dropdown-circle',
          title: '用户管理'
        },
        component: () => import('@/view/sys/systemManagement/adminUser.vue')
      },
      {
        path: 'sys/menu',
        name: 'sys/menu',
        meta: {
          icon: 'md-trending-up',
          title: '菜单管理'
        },
        component: () => import('@/view/sys/systemManagement/menu.vue')
      },
      {
        path: 'sys/role',
        name: 'sys/role',
        meta: {
          icon: 'ios-infinite',
          title: '角色管理'
        },
        component: () => import('@/view/sys/systemManagement/role.vue')
      },
      {
        path: 'sys/organ',
        name: 'sys/organ',
        meta: {
          icon: 'md-list',
          title: '机构管理'
        },
        component: () => import('@/view/sys/systemManagement/organ.vue')
      },
      {
        path: 'sys/icon',
        name: 'sys/icon',
        meta: {
          icon: 'md-list',
          title: '图标'
        },
        component: () => import('@/view/sys/systemManagement/icon.vue')
      },
    ]
  },
]
