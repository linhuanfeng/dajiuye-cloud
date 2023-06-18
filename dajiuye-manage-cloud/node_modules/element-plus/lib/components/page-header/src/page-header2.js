'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var index$2 = require('../../icon/index.js');
require('../../../hooks/index.js');
var pageHeader = require('./page-header.js');
var pluginVue_exportHelper = require('../../../_virtual/plugin-vue_export-helper.js');
var index = require('../../../hooks/use-locale/index.js');
var index$1 = require('../../../hooks/use-namespace/index.js');

const __default__ = {
  name: "ElPageHeader"
};
const _sfc_main = /* @__PURE__ */ vue.defineComponent({
  ...__default__,
  props: pageHeader.pageHeaderProps,
  emits: pageHeader.pageHeaderEmits,
  setup(__props, { emit }) {
    const { t } = index.useLocale();
    const ns = index$1.useNamespace("page-header");
    function handleClick() {
      emit("back");
    }
    return (_ctx, _cache) => {
      return vue.openBlock(), vue.createElementBlock("div", {
        class: vue.normalizeClass(vue.unref(ns).b())
      }, [
        vue.createElementVNode("div", {
          class: vue.normalizeClass(vue.unref(ns).e("left")),
          onClick: handleClick
        }, [
          _ctx.icon || _ctx.$slots.icon ? (vue.openBlock(), vue.createElementBlock("div", {
            key: 0,
            class: vue.normalizeClass(vue.unref(ns).e("icon"))
          }, [
            vue.renderSlot(_ctx.$slots, "icon", {}, () => [
              _ctx.icon ? (vue.openBlock(), vue.createBlock(vue.unref(index$2.ElIcon), { key: 0 }, {
                default: vue.withCtx(() => [
                  (vue.openBlock(), vue.createBlock(vue.resolveDynamicComponent(_ctx.icon)))
                ]),
                _: 1
              })) : vue.createCommentVNode("v-if", true)
            ])
          ], 2)) : vue.createCommentVNode("v-if", true),
          vue.createElementVNode("div", {
            class: vue.normalizeClass(vue.unref(ns).e("title"))
          }, [
            vue.renderSlot(_ctx.$slots, "title", {}, () => [
              vue.createTextVNode(vue.toDisplayString(_ctx.title || vue.unref(t)("el.pageHeader.title")), 1)
            ])
          ], 2)
        ], 2),
        vue.createElementVNode("div", {
          class: vue.normalizeClass(vue.unref(ns).e("content"))
        }, [
          vue.renderSlot(_ctx.$slots, "content", {}, () => [
            vue.createTextVNode(vue.toDisplayString(_ctx.content), 1)
          ])
        ], 2)
      ], 2);
    };
  }
});
var PageHeader = /* @__PURE__ */ pluginVue_exportHelper["default"](_sfc_main, [["__file", "/home/runner/work/element-plus/element-plus/packages/components/page-header/src/page-header.vue"]]);

exports["default"] = PageHeader;
//# sourceMappingURL=page-header2.js.map
