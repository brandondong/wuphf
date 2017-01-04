import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar from './MainNavbar.jsx';

class People extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<div className="container">People</div>
			</div>
		);
	}
}

ReactDOM.render(<People/>, document.getElementById('app'));