import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar from './MainNavbar.jsx';

class Messaging extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<div className="container">Messaging</div>
			</div>
		);
	}
}

ReactDOM.render(<Messaging/>, document.getElementById('app'));