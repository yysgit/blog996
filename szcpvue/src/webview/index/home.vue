<template>
  <!--回显-->
  <div >
  <div>

     <Row>
        <Col span="6">col-6</Col>
        <Col span="6">col-6</Col>
        <Col span="6">col-6</Col>
        <Col span="6">col-6</Col>
    </Row>
    </div>
  
  </div>
</template>

<script>

import { mapActions } from "vuex";
import { localSave, localRead } from "@/libs/util";
export default {
  name: "home",
  // 注册
  components: {
  
  },
  data() {
    return {
      
    };
  },
  created() {

  },
  computed: {
  
  },
  watch: {
  
  },
  methods: {
    ...mapActions([
    
    ]),

   
  }
};
</script>

<style lang="less">



</style>
