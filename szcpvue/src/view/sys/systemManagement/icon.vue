<template>
  <div>
    <div style="min-width:1000px">
    <Card shadow>

      <Row type="flex" justify="center" align="top" class="code-row-bg" >

        <Col span="3" v-for="item in iconList">
        <Icon :size="30" :type="item"/>
        <p class="icon-code">{{ item }}</p>
        </Col>
      </Row>

    </Card>
    </div>
  </div>
</template>

<script>
  import { mapActions } from 'vuex'
  import store from '@/store'
  export default {
    name: 'icon',
    data() {
      return {
        iconList:[],
      }
    },
    created(){
      this.getAllIconList()
    },
    methods: {
      ...mapActions([
        'getAllIcon'
      ]),
      getAllIconList(){
          this.getAllIcon().then(res => {

            //console.log("getAllIcon请求:"+res);
            this.iconList=res;
          })
      },

    }
  }
</script>

<style>
  .ivu-col-span-3 {
    display: block;
    width: 12.5%;
    text-align: center;
    margin-top: 30px;
  }
</style>
