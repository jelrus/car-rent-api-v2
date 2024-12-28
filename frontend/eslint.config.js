import globals from 'globals';
import pluginJs from '@eslint/js';
import pluginReact from 'eslint-plugin-react';
import prettier from 'eslint-plugin-prettier'; // Імпортуємо плагін Prettier

/** @type {import('eslint').Linter.Config[]} */
export default [
  {
    files: ['**/*.{js,mjs,cjs,jsx}'],
  },
  {
    languageOptions: {
      globals: globals.browser,
    },
  },
  pluginJs.configs.recommended,
  pluginReact.configs.flat.recommended,
  {
    rules: {
      'react/react-in-jsx-scope': 'off', // Вимкнути вимогу про наявність React в скоупі для JSX
      'prettier/prettier': 'error', // Включити правила Prettier для перевірки форматування
    },
    settings: {
      react: {
        version: 'detect', // Автоматичне визначення версії React
      },
    },
  },
  {
    plugins: {
      prettier: prettier, // Додаємо Prettier як плагін
    },
  },
  {
    ignores: ['node_modules/', 'dist/', 'vite.config.js'], // Correct way to ignore directories in flat config
  },
];
