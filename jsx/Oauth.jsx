import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar from './MainNavbar.jsx';

class OAuth extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<div className="container">OAuth</div>
			</div>
		);
	}
}

ReactDOM.render(<OAuth/>, document.getElementById('app'));