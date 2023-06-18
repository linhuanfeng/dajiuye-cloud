'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

require('../../../utils/index.js');
var runtime = require('../../../utils/vue/props/runtime.js');

const effects = ["light", "dark"];
const triggers = ["click", "contextmenu", "hover", "focus"];
const Effect = {
  LIGHT: "light",
  DARK: "dark"
};
const usePopperProps = runtime.buildProps({
  role: {
    type: String,
    default: "tooltip"
  }
});

exports.Effect = Effect;
exports.usePopperProps = usePopperProps;
//# sourceMappingURL=popper.js.map
