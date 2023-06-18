'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var core = require('@vueuse/core');
require('../../../utils/index.js');
var rand = require('../../../utils/rand.js');

let cachedContainer;
const POPPER_CONTAINER_ID = `el-popper-container-${rand.generateId()}`;
const POPPER_CONTAINER_SELECTOR = `#${POPPER_CONTAINER_ID}`;
const usePopperContainer = () => {
  vue.onBeforeMount(() => {
    if (!core.isClient)
      return;
    if (!cachedContainer) {
      const container = document.createElement("div");
      container.id = POPPER_CONTAINER_ID;
      document.body.appendChild(container);
      cachedContainer = container;
    }
  });
};

exports.POPPER_CONTAINER_ID = POPPER_CONTAINER_ID;
exports.POPPER_CONTAINER_SELECTOR = POPPER_CONTAINER_SELECTOR;
exports.usePopperContainer = usePopperContainer;
//# sourceMappingURL=container.js.map
