import { iniciarApi } from './api-server.js';
import { iniciarMcp } from './mcp-server.js';

const apiPorta = Number(process.env.API_PORT ?? process.env.PORT ?? 3000);
const mcpPorta = Number(process.env.MCP_PORT ?? 3001);

await Promise.all([iniciarApi(apiPorta), iniciarMcp(mcpPorta)]);
