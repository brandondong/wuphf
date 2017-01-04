import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar from './MainNavbar.jsx';

class Index extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<div className="container">Hello</div>
			</div>
		);
	}
}

ReactDOM.render(<Index/>, document.getElementById('app'));