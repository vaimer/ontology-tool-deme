module.exports= {
    extends: [
        "eslint:recommended",
        "plugin:@typescript-eslint/recommended",
        "plugin:react-hooks/recommended"
    ],
    parser: "@typescript-eslint/parser",
    parserOptions: { project: ["./tsconfig.json"], tsconfigRootDir: "./" },
    plugins: [
        "prettier"
    ],
    env: {
        browser: true,
        es6: true,
        node: true,
    },
    settings: {
        react: {
            version: "ditect",
        },
        "import/resolver": {
            node: {
                extensions: [".js", ".jsx", ".ts", ".tsx"],
                paths: ["./src"]
            },
            webpack: {
                config: 'webpack.common.js'
            }
        }
    },
    rules: {
        "prettier/prettier": ["warn"],
        "@typescript-eslint/strict-boolean-expressions": [
            2,
            {
                allowStrin : false,
                allowNumber : false
            }
        ]
    },
    ignorePatterns: ["src/**/*.test.ts", "src/frontend/generated/*"]
}