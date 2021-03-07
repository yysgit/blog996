<template>
  <div style=" margin-top: 3.6rem;">
    <div class="left_menu">
      <Menu active-name="1-2" :open-names="['1']" style="width: 100%;">
        <Submenu name="1">
          <template slot="title">
            <Icon type="ios-analytics" />Navigation One
          </template>
          <MenuGroup title="Item 1">
            <MenuItem name="1-1">Option 1</MenuItem>
            <MenuItem name="1-2">Option 2</MenuItem>
          </MenuGroup>
          <MenuGroup title="Item 2">
            <MenuItem name="1-3">Option 3</MenuItem>
            <MenuItem name="1-4">Option 4</MenuItem>
          </MenuGroup>
        </Submenu>
        <Submenu name="2">
          <template slot="title">
            <Icon type="ios-filing" />Navigation Two
          </template>
          <MenuItem name="2-1">Option 5</MenuItem>
          <MenuItem name="2-2">Option 6</MenuItem>
          <Submenu name="3">
            <template slot="title">Submenu</template>
            <MenuItem name="3-1">Option 7</MenuItem>
            <MenuItem name="3-2">Option 8</MenuItem>
          </Submenu>
        </Submenu>
        <Submenu name="4">
          <template slot="title">
            <Icon type="ios-cog" />Navigation Three
          </template>
          <MenuItem name="4-1">Option 9</MenuItem>
          <MenuItem name="4-2">Option 10</MenuItem>
          <MenuItem name="4-3">Option 11</MenuItem>
          <MenuItem name="4-4">Option 12</MenuItem>
        </Submenu>
      </Menu>
    </div>
    <router-view />
    <div style="text-align: center;">©2021 blog996.com 版权所有 ICP证: 豫ICP备2021005024号-1</div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "index_page",
  data() {
    return {};
  },
  created() {},
  mounted() {},
  methods: {
    ...mapActions([])
  }
};
</script>

<style lang="less">
.left_menu {
  font-size: 16px;
  background-color: #fff;
  width: 20rem;
  position: fixed;
  z-index: 10;
  margin: 0;
  top: 3.6rem;
  left: 0;
  bottom: 0;
  box-sizing: border-box;
  border-right: 1px solid #eaecef;
  overflow-y: auto;
  z-index: 11000;
  .ivu-menu-light.ivu-menu-vertical
    .ivu-menu-item-active:not(.ivu-menu-submenu) {
    background: #ffffff;
  }
  .ivu-menu-light.ivu-menu-vertical
    .ivu-menu-item-active:not(.ivu-menu-submenu):after {
    width: 1px;
    background: #dcdee2;
  }
}

@media (max-width: 719px) {
  .left_menu {
    top: 0;
    padding-top: 3.6rem;
    transform: translateX(-100%);
    transition: transform 0.2s ease;
  }
}
@media (max-width: 959px) {
  .left_menu {
    font-size: 15px;
    width: 16.4rem;
  }
}
</style>
