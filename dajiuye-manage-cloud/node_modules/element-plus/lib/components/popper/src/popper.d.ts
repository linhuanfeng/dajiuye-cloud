import type { ExtractPropTypes } from 'vue';
declare const effects: readonly ["light", "dark"];
declare const triggers: readonly ["click", "contextmenu", "hover", "focus"];
export declare const Effect: {
    LIGHT: string;
    DARK: string;
};
export declare type PopperEffect = typeof effects[number];
export declare type PopperTrigger = typeof triggers[number];
export declare const usePopperProps: {
    readonly role: import("element-plus/es/utils").EpPropFinalized<(new (...args: any[]) => ("listbox" | "grid" | "menu" | "tooltip" | "dialog" | "tree") & {}) | (() => "listbox" | "grid" | "menu" | "tooltip" | "dialog" | "tree") | ((new (...args: any[]) => ("listbox" | "grid" | "menu" | "tooltip" | "dialog" | "tree") & {}) | (() => "listbox" | "grid" | "menu" | "tooltip" | "dialog" | "tree"))[], unknown, unknown, "tooltip", boolean>;
};
export declare type UsePopperProps = ExtractPropTypes<typeof usePopperProps>;
export {};
