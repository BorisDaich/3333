import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICatalogcolors, NewCatalogcolors } from '../catalogcolors.model';

export type PartialUpdateCatalogcolors = Partial<ICatalogcolors> & Pick<ICatalogcolors, 'id'>;

export type EntityResponseType = HttpResponse<ICatalogcolors>;
export type EntityArrayResponseType = HttpResponse<ICatalogcolors[]>;

@Injectable({ providedIn: 'root' })
export class CatalogcolorsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/catalogcolors');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(catalogcolors: NewCatalogcolors): Observable<EntityResponseType> {
    return this.http.post<ICatalogcolors>(this.resourceUrl, catalogcolors, { observe: 'response' });
  }

  update(catalogcolors: ICatalogcolors): Observable<EntityResponseType> {
    return this.http.put<ICatalogcolors>(`${this.resourceUrl}/${this.getCatalogcolorsIdentifier(catalogcolors)}`, catalogcolors, {
      observe: 'response',
    });
  }

  partialUpdate(catalogcolors: PartialUpdateCatalogcolors): Observable<EntityResponseType> {
    return this.http.patch<ICatalogcolors>(`${this.resourceUrl}/${this.getCatalogcolorsIdentifier(catalogcolors)}`, catalogcolors, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ICatalogcolors>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICatalogcolors[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCatalogcolorsIdentifier(catalogcolors: Pick<ICatalogcolors, 'id'>): string {
    return catalogcolors.id;
  }

  compareCatalogcolors(o1: Pick<ICatalogcolors, 'id'> | null, o2: Pick<ICatalogcolors, 'id'> | null): boolean {
    return o1 && o2 ? this.getCatalogcolorsIdentifier(o1) === this.getCatalogcolorsIdentifier(o2) : o1 === o2;
  }

  addCatalogcolorsToCollectionIfMissing<Type extends Pick<ICatalogcolors, 'id'>>(
    catalogcolorsCollection: Type[],
    ...catalogcolorsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const catalogcolors: Type[] = catalogcolorsToCheck.filter(isPresent);
    if (catalogcolors.length > 0) {
      const catalogcolorsCollectionIdentifiers = catalogcolorsCollection.map(
        catalogcolorsItem => this.getCatalogcolorsIdentifier(catalogcolorsItem)!
      );
      const catalogcolorsToAdd = catalogcolors.filter(catalogcolorsItem => {
        const catalogcolorsIdentifier = this.getCatalogcolorsIdentifier(catalogcolorsItem);
        if (catalogcolorsCollectionIdentifiers.includes(catalogcolorsIdentifier)) {
          return false;
        }
        catalogcolorsCollectionIdentifiers.push(catalogcolorsIdentifier);
        return true;
      });
      return [...catalogcolorsToAdd, ...catalogcolorsCollection];
    }
    return catalogcolorsCollection;
  }
}
