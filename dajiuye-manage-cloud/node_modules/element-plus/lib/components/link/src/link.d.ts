import type { ExtractPropTypes } from 'vue';
import type Link from './link.vue';
export declare const linkProps: {
    readonly type: import("element-plus/es/utils").EpPropFinalized<StringConstructor, "default" | "primary" | "success" | "warning" | "info" | "danger", unknown, "default", boolean>;
    readonly underline: import("element-plus/es/utils").EpPropFinalized<BooleanConstructor, unknown, unknown, true, boolean>;
    readonly disabled: import("element-plus/es/utils").EpPropFinalized<BooleanConstructor, unknown, unknown, false, boolean>;
    readonly href: import("element-plus/es/utils").EpPropFinalized<StringConstructor, unknown, unknown, "", boolean>;
    readonly icon: import("element-plus/es/utils").EpPropFinalized<(new (...args: any[]) => (string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>) & {}) | (() => string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>) | ((new (...args: any[]) => (string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>) & {}) | (() => string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>))[], unknown, unknown, "", boolean>;
};
export declare type LinkProps = ExtractPropTypes<typeof linkProps>;
export declare const linkEmits: {
    click: (evt: MouseEvent) => boolean;
};
export declare type LinkEmits = typeof linkEmits;
export declare type LinkInstance = InstanceType<typeof Link>;
