// If you want to expose your class to the Global scope
// to be used by `window` in the browser
// You need to set in globalThis
// [https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/globalThis]

class FooBar {
  // relevant code here
}

globalThis.FooBar = FooBar;

export { FooBar };

// Works with esbuild
