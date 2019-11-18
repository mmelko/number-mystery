import * as React from 'react';
import { Statistics } from '../Model/Statistics';
import { Card, CardHeader, CardBody, GridItem, Grid } from '@patternfly/react-core';
import { Table, TableHeader, TableBody, } from '@patternfly/react-table';


const NumberCard: React.FunctionComponent<any> = (props) => {
    return <Card>
        <CardHeader>{props.header}</CardHeader>
        <CardBody>

            {props.number}</CardBody>
    </Card>;
};

const StatisticsGrid: React.FunctionComponent<Statistics> = (props) => {
    return <Grid gutter='md'>
        <GridItem span={4} offset={2} rowSpan={2}>
            <NumberCard header='SecretNumber' number={props.secretNumber} />
        </GridItem>
        <GridItem span={4} offset={6} rowSpan={2}>
            <NumberCard header='Number of guesses' number={props.attempts} />
        </GridItem>
        <GridItem span={4} offset={2} rowSpan={2}>
            <NumberCard header='Guesed' number={props.guessed} />
        </GridItem>
        <GridItem span={4} offset={6} rowSpan={2}>
            <NumberCard header='Last One' number={props.lastOne}/>
            </GridItem>
    </Grid>;
}


type integrationProps = {
    integrations: Integration[],
    activities: Activities[]
};

const IntegrationsTable: React.FunctionComponent<integrationProps> = (props) => {
    //  const { columns, rows } = this.state;
    const columns = ["name", "state", "number of activities", "last activity"];

    const rows = props.integrations.map(integration => {
        var activity = props.activities.find(a => a.integrationName === integration.name);

        return {
            cells: [integration.name,
            integration.currentState,
            activity != null ? activity.activitiesCount : "N/A"
                , activity != null ? activity.lastOne + "" : "N/A"
            ]
        };
    });

    return (
        <Table caption="Integrations" cells={columns} rows={rows}>
            <TableHeader />
            <TableBody />
        </Table>
    );
}

export { StatisticsGrid, IntegrationsTable };