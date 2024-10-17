<script setup lang="ts">
import {baseURL} from "../../api/request";
import router from "../../router";

window.open(baseURL + '/druid/index.html', '_blank')
router.go(-1)
</script>