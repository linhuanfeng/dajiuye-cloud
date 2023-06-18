'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

const RepeatClick = {
  beforeMount(el, binding) {
    let interval = null;
    let isHandlerCalled = false;
    const handler = () => binding.value && binding.value();
    const clear = () => {
      clearInterval(interval);
      interval = null;
      if (!isHandlerCalled) {
        handler();
      }
      isHandlerCalled = false;
    };
    el.addEventListener("mousedown", (e) => {
      if (e.button !== 0)
        return;
      document.addEventListener("mouseup", clear, { once: true });
      clearInterval(interval);
      interval = setInterval(() => {
        isHandlerCalled = true;
        handler();
      }, 100);
    });
  }
};

exports["default"] = RepeatClick;
//# sourceMappingURL=index.js.map
