var config = {
	entry: {
		index: './jsx/Index.jsx',
		integrations: './jsx/Integrations.jsx',
		people: './jsx/People.jsx',
		messaging: './jsx/Messaging.jsx',
		oauth: './jsx/OAuth.jsx'
	},

	output: {
		path:'./WebContent/js',
		filename: '[name].entry.js',
	},

	module: {
		loaders: [{
			test: /\.jsx?$/,
		    exclude: /node_modules/,
		    loader: 'babel',

		    query: {
		    	presets: ['es2015', 'react']
		    }
		}]
	}
}

module.exports = config;