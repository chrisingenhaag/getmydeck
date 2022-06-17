/** @type {import('@sveltejs/kit').ParamMatcher} */
export function match(param) {
  return /^(64|256|512)$/.test(param);
}