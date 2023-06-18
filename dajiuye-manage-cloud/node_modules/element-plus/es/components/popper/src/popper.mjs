import '../../../utils/index.mjs';
import { buildProps } from '../../../utils/vue/props/runtime.mjs';

const effects = ["light", "dark"];
const triggers = ["click", "contextmenu", "hover", "focus"];
const Effect = {
  LIGHT: "light",
  DARK: "dark"
};
const usePopperProps = buildProps({
  role: {
    type: String,
    default: "tooltip"
  }
});

export { Effect, usePopperProps };
//# sourceMappingURL=popper.mjs.map
