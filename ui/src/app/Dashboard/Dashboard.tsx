import * as React from 'react';
import { Statistics } from '../Model/Statistics';
import { Integration } from '../Model/Integration';
import { Activities } from '../Model/Activities';
import { StatisticsGrid,IntegrationsTable } from './StatisticsComponents';
import * as StatsService from '../Services/StatsService';

import { PageSection, Title, Card, Grid, GridItem } from '@patternfly/react-core';

type state = {
  stats: Statistics,
  integrations: Integration[],
  activities: Activities[];

}
class Dashboard extends React.Component<{ name: string }, state> {
  constructor(props: { name: string }) {
    super(props);
    this.state =
      {
        stats: new Statistics(0, 0, 0, 0),
        integrations: [{ name: "test", currentState: "b" }, { name: "b", currentState: "c" }],
        activities: [{ integrationName: "test", activitiesCount: 1, lastOne: new Date() }]
      };
      this.updateStats();
    setInterval(this.updateStats, 5500);
  }

  public render() {
    return <PageSection>
      <Title size="lg">Number-creator status</Title>
      <StatisticsGrid
        secretNumber={this.state.stats.secretNumber}
        attempts={this.state.stats.attempts}
        guessed={this.state.stats.guessed}
        lastOne={this.state.stats.lastOne} />
        <div></div>
      <Grid gutter='md'>
        <GridItem span={8} offset={2}>
          <IntegrationsTable activities={this.state.activities} integrations={this.state.integrations} /></GridItem>
       
      </Grid>
    </PageSection>
  }

  private updateStats = () => {
    const current = this.state;
    StatsService.getStats().then(statistics => {
      this.setState({ stats: statistics });
    });

    Promise.all([StatsService.getIntegrations(),StatsService.getActivities()]).then(values =>
      {
        this.setState({
          integrations:values[0],
          activities: values[1]
        });
      });
  };
}
export { Dashboard };
