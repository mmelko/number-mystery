import * as React from 'react';
import { Statistics } from '../Model/Statistics';
import { Card, CardHeader, CardBody, GridItem, Grid } from '@patternfly/react-core';




const NumberCard: React.FunctionComponent<any> = (props) => {
    return <Card>
        <CardHeader>{props.header}</CardHeader>
        <CardBody>{props.number}</CardBody>
    </Card>;
};

class StatisticsGrid extends React.Component<Statistics, {}> {
    constructor(props: Statistics) {
        super(props);
    }
    public render() {
        return <Grid gutter='md'>
            <GridItem span={4} offset ={2} rowSpan={2}>
                <NumberCard header='SecretNumber' number={this.props.secretNumber} />
            </GridItem>

            <GridItem span={4} offset ={6} rowSpan={2}>
                <NumberCard header='Number of guesses' number={this.props.attempts} />
            </GridItem>
            <GridItem span={4} offset ={2} rowSpan={2}>
                <NumberCard header='Guesed' number={this.props.guessed} />
            </GridItem>
            <GridItem span={4} offset ={6} rowSpan={2}>
                <NumberCard header='Last One' number={this.props.lastOne} />I
            </GridItem>
        </Grid>;
    }
}
export { StatisticsGrid };