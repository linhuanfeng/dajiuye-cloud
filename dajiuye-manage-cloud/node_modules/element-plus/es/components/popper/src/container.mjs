import { onBeforeMount } from 'vue';
import { isClient } from '@vueuse/core';
import '../../../utils/index.mjs';
import { generateId } from '../../../utils/rand.mjs';

let cachedContainer;
const POPPER_CONTAINER_ID = `el-popper-container-${generateId()}`;
const POPPER_CONTAINER_SELECTOR = `#${POPPER_CONTAINER_ID}`;
const usePopperContainer = () => {
  onBeforeMount(() => {
    if (!isClient)
      return;
    if (!cachedContainer) {
      const container = document.createElement("div");
      container.id = POPPER_CONTAINER_ID;
      document.body.appendChild(container);
      cachedContainer = container;
    }
  });
};

export { POPPER_CONTAINER_ID, POPPER_CONTAINER_SELECTOR, usePopperContainer };
//# sourceMappingURL=container.mjs.map
