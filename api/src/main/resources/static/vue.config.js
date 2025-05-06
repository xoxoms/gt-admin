module.exports = {
  devServer: {
    disableHostCheck: true,
    port: 3000,
    proxy: 'http://localhost:8080'
  },

  transpileDependencies: ['vuetify'],

  pluginOptions: {
    i18n: {
      locale: 'en',
      fallbackLocale: 'en',
      localeDir: 'locales',
      enableInSFC: false,
    },
  },
}
