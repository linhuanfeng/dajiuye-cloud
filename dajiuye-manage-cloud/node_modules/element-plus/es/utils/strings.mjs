export { camelize, capitalize, hyphenate, hyphenate as kebabCase } from '@vue/shared';

const escapeStringRegexp = (string = "") => string.replace(/[|\\{}()[\]^$+*?.]/g, "\\$&").replace(/-/g, "\\x2d");

export { escapeStringRegexp };
//# sourceMappingURL=strings.mjs.map
