<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="less">
.size{
  width: 100%;
  height: 100%;
  overflow: auto;
}
html,body{
  .size;
  overflow: hidden;
  margin: 0;
  padding: 0;
}
body {
  font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-size: 16px;
  color: #2c3e50;
}


a {
  color: #fec41b;
  font-weight: 500;
  text-decoration: none;
  line-height: revert;
}

input::-webkit-input-placeholder{
  color:#fec41b!important;
}
input:-moz-placeholder{
  color:#fec41b!important;
}
input::-moz-placeholder{
  color:#fec41b!important;
}
input:-ms-input-placeholder{
  color:#fec41b!important;
}
input{
  color:#fec41b!important;
}


textarea::-webkit-input-placeholder{
  color:#fec41b!important;
}
textarea:-moz-placeholder{
  color:#fec41b!important;
}
textarea::-moz-placeholder{
  color:#fec41b!important;
}
textarea:-ms-input-placeholder{
  color:#fec41b!important;
}
textarea{
  color:#fec41b!important;
}

.ivu-select-selected-value{
    color:#fec41b!important;
}
.ivu-select-placeholder{
    color:#fec41b!important;
}

#app {
  .size;
}
</style>
