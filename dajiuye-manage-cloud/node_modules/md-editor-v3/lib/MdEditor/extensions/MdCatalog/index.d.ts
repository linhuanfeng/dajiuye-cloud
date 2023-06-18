import { PropType, ExtractPropTypes } from 'vue';
import { MarkedHeadingId, Themes } from '../../type';
export interface TocItem {
    text: string;
    level: number;
    index: number;
    children?: Array<TocItem>;
}
declare const MdCatalog: import("vue").DefineComponent<{
    editorId: {
        type: PropType<string>;
    };
    class: {
        type: StringConstructor;
        default: string;
    };
    markedHeadingId: {
        type: PropType<MarkedHeadingId>;
        default: (text: string) => string;
    };
    scrollElement: {
        type: PropType<string | Element>;
    };
    theme: {
        type: PropType<Themes>;
        default: string;
    };
}, () => JSX.Element, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, "onClick"[], "onClick", import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<ExtractPropTypes<{
    editorId: {
        type: PropType<string>;
    };
    class: {
        type: StringConstructor;
        default: string;
    };
    markedHeadingId: {
        type: PropType<MarkedHeadingId>;
        default: (text: string) => string;
    };
    scrollElement: {
        type: PropType<string | Element>;
    };
    theme: {
        type: PropType<Themes>;
        default: string;
    };
}>> & {
    onOnClick?: ((...args: any[]) => any) | undefined;
}, {
    theme: Themes;
    class: string;
    markedHeadingId: MarkedHeadingId;
}>;
export default MdCatalog;
