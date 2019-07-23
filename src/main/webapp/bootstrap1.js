import Vue from 'vue';
import ElementUI from 'element-ui';
import './css/style.scss';
import VueSplit from 'vue-split-panel'
import * as monaco from 'monaco-editor/esm/vs/editor/editor.api';

window.Vue = Vue;
window.monaco = monaco;

Vue.use(ElementUI, { size: 'mini', zIndex: 3000 });
Vue.use(VueSplit);

