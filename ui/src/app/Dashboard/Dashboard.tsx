import * as React from 'react';
import { Statistics } from '../Model/Statistics';
import { StatisticsGrid } from './StatisticsGrid';
import * as StatsService from '../Services/StatsService';
import { PageSection, Title, Card } from '@patternfly/react-core';


class Dashboard extends React.Component<{ name: string }, Statistics> {
  constructor(props: { name: string }) {
    super(props);
    this.state = new Statistics(0, 0, 0, 0);
    setInterval(this.updateStats, 2500);
  }

  public render() {
    return <PageSection>
      <Title size="lg">Number-creator status</Title>
      <StatisticsGrid
        secretNumber={this.state.secretNumber}
        attempts={this.state.attempts}
        guessed={this.state.guessed}
        lastOne={this.state.lastOne} />
    </PageSection>
  }

  private updateStats = () => {
    const current = this.state;
    StatsService.getStats().then(stats => {
      this.setState(stats);
    });


  };
}
export { Dashboard };
