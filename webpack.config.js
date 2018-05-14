const path = require('path');


module.exports = {
    entry: './web/assets/src/main.js',
    output: {
        path: path.resolve(__dirname, 'web/assets/dist'),
        filename: 'main.js'
    },
    mode: 'production',
    module: {
        rules: [
            {
                test: /\.js$/,
                loader: 'babel-loader'
            }
        ]
    }
}