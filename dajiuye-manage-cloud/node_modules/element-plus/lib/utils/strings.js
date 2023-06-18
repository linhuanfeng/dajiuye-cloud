'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var shared = require('@vue/shared');

const escapeStringRegexp = (string = "") => string.replace(/[|\\{}()[\]^$+*?.]/g, "\\$&").replace(/-/g, "\\x2d");

Object.defineProperty(exports, 'camelize', {
  enumerable: true,
  get: function () { return shared.camelize; }
});
Object.defineProperty(exports, 'capitalize', {
  enumerable: true,
  get: function () { return shared.capitalize; }
});
Object.defineProperty(exports, 'hyphenate', {
  enumerable: true,
  get: function () { return shared.hyphenate; }
});
Object.defineProperty(exports, 'kebabCase', {
  enumerable: true,
  get: function () { return shared.hyphenate; }
});
exports.escapeStringRegexp = escapeStringRegexp;
//# sourceMappingURL=strings.js.map
